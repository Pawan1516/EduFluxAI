import React, { useState, useRef, useEffect, useCallback } from 'react';
import * as api from '../services/apiService';
import { ChatMessage } from '../types';
import { decode, decodeAudioData } from '../utils/audio';

interface ChatbotProps {
    userId: string;
}

const AIAssistantChatbot: React.FC<ChatbotProps> = ({ userId }) => {
    const [isOpen, setIsOpen] = useState(false);
    const [messages, setMessages] = useState<ChatMessage[]>([]);
    const [input, setInput] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const messagesEndRef = useRef<HTMLDivElement | null>(null);
    const audioContextRef = useRef<AudioContext | null>(null);

    useEffect(() => {
        if (isOpen && messages.length === 0) {
            setMessages([{ sender: 'ai', text: "Hello! I'm your AI Smart Tutor. How can I help you today?" }]);
            if (!audioContextRef.current) {
                try {
                    audioContextRef.current = new (window.AudioContext || (window as any).webkitAudioContext)({ sampleRate: 24000 });
                } catch (e) { console.error("Web Audio API not supported.", e); }
            }
        }
    }, [isOpen, messages.length]);

    useEffect(() => {
        messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
    }, [messages]);

    const playAudio = useCallback(async (base64: string) => {
        if (!audioContextRef.current) return;
        try {
            const audioBytes = decode(base64);
            const audioBuffer = await decodeAudioData(audioBytes, audioContextRef.current, 24000, 1);
            const source = audioContextRef.current.createBufferSource();
            source.buffer = audioBuffer;
            source.connect(audioContextRef.current.destination);
            source.start();
        } catch (error) { console.error("Failed to play audio:", error); }
    }, []);

    const handleSend = useCallback(async () => {
        if (input.trim() === '' || isLoading) return;

        const userMessage: ChatMessage = { sender: 'user', text: input };
        setMessages(prev => [...prev, userMessage, { sender: 'ai',